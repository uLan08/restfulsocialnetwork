package com.patrickjuen.restsocnet

class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	static hasMany = [posts: Post, likedPost: Post, followedUsers: User, followers: User, notifications: Notification]
    static mappedBy = [posts: "user", notifications: "user"]
    

	User(String username, String password) {
		this()
		this.username = username
		this.password = password
	}

	@Override
	int hashCode() {
		username?.hashCode() ?: 0
	}

	@Override
	boolean equals(other) {
		is(other) || (other instanceof User && other.username == username)
	}

	@Override
	String toString() {
		username
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false, minSize: 6
        likedPost nullable: true
	}

	static mapping = {
		password column: '`password`'
		posts lazy: false, sort: 'dateCreated', order: 'desc'
		likedPost fetch: 'join'
//		 followedUsers column:'User_Followed_Id', joinTable: 'USER_USER', key: 'Follower_Id'
		// follower column: 'Follower_Id', joinTable: 'USER_USER', index: 'User_Followed_Id'
		followedUsers joinTable: [name: 'USER_USER', key: 'Follower_Id', column: 'User_Followed_Id'], fetch: 'join'
		followers joinTable: [name: 'USER_USER', key: 'User_Followed_Id', column: 'Follower_Id'], fetch: 'join'

	}

	boolean isCurrentUser(){
		return this.username == springSecurityService.currentUser
	}

	boolean hasFollowed(User user){
		for(followed in this.followedUsers){
			if(followed == user){
				return true
			}
		}
	}
}
