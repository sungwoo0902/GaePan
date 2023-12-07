$(document).ready(function() {
    const path = "/admin/board/deleteBoardList";

    const path2 = "/admin/board/deleteById";
    const path3 = "/admin/board/list";

    // 체크박스 통해서 삭제
    $(".deleteBtn").click(function(){
        if(confirm("정말 삭제 하시겠습니까?")){
            const checked = $(".checkbox:checked");

            // check를 여러개 할 수 있기 때문에 배열로 초기화
            const boardBno = [];
            for(let i = 0; i < checked.length; i++){
                const currentValue = checked[i].value * 1;
                console.log("Current value: " + currentValue);

                boardBno.push(
                    { bno : currentValue }
                );
            }
            console.log("boardBno:", boardBno);

            // memberNo에 값이 있을 때만 ajax로 처리
            // -> memberType이 1 or 2일 경우만 해당
            if(boardBno.length > 0){
                console.log("3");
                $.ajax({
                    url: path,
                    type: "POST",
                    traditional: true, // 배열을 직렬화 하는 옵션
                    contentType: 'application/json',
                    data: JSON.stringify(boardBno),
                    success: function(data){
                        console.log("Success:", data);
                        alert("게시글이 삭제되었습니다.");
                        location.reload();
                    },
                    error: function(xhr, status, error){
                        console.error("Error:", error);
                        alert("게시글 삭제에 실패했습니다.");
                    }
                }); // ajax end
            }// if > ajax end
        } // if end
    });// click end

    // 개별 삭제
    $(".board_deleteBtn").click(function(e) {
        e.preventDefault();
        const bno = $(this).data("bno");
        // const uid = $(".admin__uid").data("uid");
        const group = $(".board__group").data("group");
        const cate = $(this).data("cate");
        console.log("bno : " + bno);
        console.log("group : " + group);
        console.log("cate : " + cate);

        // debugger;
        if(confirm("정말 게시글을 삭제하시겠습니까?")){
            $.ajax({
                type: "delete",
                url: path2,
                data: {
                    bno : bno
                },
                success: function(response) {
                    console.log(1);
                    alert("게시글이 삭제되었습니다.");
                    location.replace(path3+"?group="+group+"&cate="+cate);
                },
                error: function(error) {
                    console.log(2);
                    alert("게시글 삭제에 실패했습니다.");
                }
            }); // ajax end
        } // confirm if end
    }); // deleteBtn end

});