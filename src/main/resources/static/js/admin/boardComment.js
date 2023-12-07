const path3 = "/admin/board/deleteComment";
const path4 = "/admin/board/view";

const path5 = "/admin/board/modifyComment";

$(document).ready(function() {
    // 초기에는 댓글 수정, 댓글 삭제만 보이도록 설정
    $('.modifyCancel, .modifyComplete').hide();

    // 댓글 수정, 작성 취소, 수정 완료 버튼 클릭 시 토글
    $('.comment__modify, .modifyCancel, .modifyComplete').click(function() {
        $(this).parent()
            .find('.comment__modify, .comment__delete, .modifyCancel, .modifyComplete')
            .toggle();
    });
});

$(".comment__modify").click(function(e) {
    e.preventDefault();

    const bno = $(this).data('cbno');
    const parent = $(this).data('parent');
    const comment = $(this).data('comment');

    console.log("bno : " + bno);
    console.log("parent : " + parent);
    console.log("comment : " + comment);

    // 수정 전 댓글 저장
    const originalComment = comment;

    // 수정 하기 위해 textArea 찾기
    // closest : 가장 가까운 부모 찾음
    // siblings : 찾은 부모안에 해당 형제요소가 있는지 선택
    // -> .admin_board_left안에서 .comment 요소를 선택하는거임
    let commentTextarea = $(this).closest('.admin_board_left')
                                .siblings('.comment')
                                .find('.commentArea');
    commentTextarea.attr('readonly', false).focus();

    // focus() 이후에 setSelectionRange()를 사용하여 텍스트 맨 끝에 커서를 두도록 함
    const textLength = commentTextarea.val().length;
    commentTextarea[0].setSelectionRange(textLength, textLength);
    
    // 댓글 수정 할 수 있게끔 div class값 수정
    commentTextarea.closest('.comment')
                    .removeClass()
                    .addClass("modifyCommentDiv");

    $(".modifyComplete").click(function(e) {
        e.preventDefault();

        const bno = $(this).data("cbno");
        const parent = $(this).data("parent");
        // 수정된 내용을 가져옴
        const comment = commentTextarea.val();
        const cate = $(this).data("cate");
        // const cate = $(".commentArea").data("cate");
        console.log("bno : " + bno);
        console.log("parent : " + parent);
        console.log("comment : " + comment);
        console.log("cate : " +cate);

        // debugger;

        $.ajax({
            type: "put",
            url: path5,
            data: {
                bno : bno,
                comment : comment
            },
            success: function(response) {
                console.log("댓글 수정");
                commentTextarea.closest('.comment').removeClass().addClass(".comment");
                location.replace(path4+"?bno="+parent+"&cate="+cate);
            },
            error: function(error) {
                console.log("댓글 수정 실패");
            }
        }); // ajax end
    });

    $(".modifyCancel").click(function (e) {
        e.preventDefault();

        // 수정하기 전 댓글
        commentTextarea.val(originalComment);

        commentTextarea.attr('readonly', true);
        commentTextarea.closest('.modifyCommentDiv').removeClass().addClass("comment");
    })
});


$(".comment__delete").click(function(e) {
    e.preventDefault();

    const bno = $(this).data("cbno");
    const parent = $(this).data("parent");
    const cate = $(this).data("cate");

    console.log("bno : " + bno);
    console.log("parent : " + parent);
    console.log("cate : " +cate);

    // debugger;

    if(confirm("정말 댓글 삭제 하시겠습니까?")){
        $.ajax({
            type: "delete",
            url: path3,
            data: {
                bno : bno
            },
            success: function(response) {
                alert("댓글이 삭제되었습니다.");
                location.replace(path4+"?bno="+parent+"&cate="+cate);
            },
            error: function(error) {
                alert("댓글 삭제에 실패했습니다.");
            }
        }); // ajax end
    }
});
