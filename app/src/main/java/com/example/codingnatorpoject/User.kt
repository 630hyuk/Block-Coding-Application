package com.example.codingnatorpoject

data class User ( //AWS에 보관되어 있는 유저의 정보들을 위한 클래스입니다. 백엔드 팀원분들은 이곳을 참고해서 추가하시거나 제외하시면 될거 같습니다.
    var email : String,
    var nickname: String,
    var pw: String,
    var stars: Int
) : Comparable<User> {
    override fun compareTo(other: User): Int {
        return stars - other.stars
    }

}