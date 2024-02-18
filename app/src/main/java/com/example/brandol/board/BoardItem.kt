package com.example.brandol.board

data class BoardItem (
    val writerId: String, // 작성자 ID
    val writerProfile: String, // 작성자 프로필 이미지 URL
    val writerName: String, // 작성자 이름
    val title: String, // 게시물 제목
    val content: String, // 게시물 내용
    val images: List<String>, // 게시물 이미지 URL 리스트
    val likeCount: Int, // 좋아요 수
    val commentCount: Int, // 댓글 수
    val writtenDate: String // 작성 날짜
)