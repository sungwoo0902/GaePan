$(function() {
    const path = "/admin/member/blackListCheck"

    $(".blackList_Btn").click(function(){
        if(confirm("정말 블랙리스트 등록 하시겠습니까?")){
            console.log("1");
            const checked = $(".checkbox:checked");
            // check를 여러개 할 수 있기 때문에 배열로 초기화
            const memberNo = [];
            for(let i = 0; i < checked.length; i++){
                // memberRoles에 따라 로직 다르게 처리
                const memberRoles = $(checked[i]).closest("tr")
                                                .find(".memberRoles")
                                                .data("role");
                // 배열에 값을 넣어 주기 위해 push
                if(memberRoles === 1 || memberRoles === 2){
                    memberNo.push(
                        // no : 어떤 것을 check 했는지 확인하는 pk값
                        { no : checked[i].value * 1 }
                    );
                    console.log("4");
                }else if(memberRoles === 99){
                    alert("관리자는 변경 할 수 없습니다.");
                }else if(memberRoles === 4)
                    alert("탈퇴자는 변경 할 수 없습니다.");
                }

            // memberNo에 값이 있을 때만 ajax로 처리
            // -> memberType이 1 or 2일 경우만 해당
            if(memberNo.length > 0){
                $.ajax({
                    url: path,
                    type: "POST",
                    traditional: true, // 배열을 직렬화 하는 옵션
                    contentType: 'application/json',
                    data: JSON.stringify(memberNo),
                    success: function(data){
                        alert("블랙리스트로 등록 되었습니다.");
                        location.reload();
                    },
                    error: function(xhr, status, error){
                        console.log("블랙 등록 실패");
                        console.log(xhr.responseText); // 추가
                        console.log(status); // 추가
                        console.log(error); // 추가
                    }
                }); // ajax end
            }// if > ajax end
        } // if end
    });// click end
});