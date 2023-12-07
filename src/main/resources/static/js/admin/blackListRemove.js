$(function() {
    const path = "/admin/member/blackListRemove"

    $(".blackListRemove_Btn").click(function(){
        if(confirm("정말 블랙리스트 해제 하시겠습니까?")){
            const checked = $(".checkbox:checked");
            // check를 여러개 할 수 있기 때문에 배열로 초기화
            const memberNo = [];
            for(let i = 0; i < checked.length; i++) {
                memberNo.push(
                    // no : 어떤 것을 check 했는지 확인하는 pk값
                    {no: checked[i].value * 1}
                );
            }

            $.ajax({
                url: path,
                type: "POST",
                traditional: true, // 배열을 직렬화 하는 옵션
                contentType: 'application/json',
                data: JSON.stringify(memberNo),
                success: function(data){
                    alert("블랙리스트로 해제 되었습니다.");
                    location.reload();
                },
                error: function(){
                    console.log("블랙리스트 해제 실패");
                }
            }); // ajax end
        } // if end
    });// click end
});