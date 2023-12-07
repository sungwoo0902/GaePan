$(function() {
    const path = "/admin/member/leaveCheck"

    $(".leaveMember_Btn").click(function(){
        if(confirm("정말 탈퇴 처리 하시겠습니까?")){
            const checked = $(".checkbox:checked");
            const memberNo = [];
            for(let i = 0; i < checked.length; i++){
                memberNo.push(
                    { no : checked[i].value * 1 }
                );
            }
            $.ajax({
                url: path,
                type: "POST",
                traditional: true,
                contentType: 'application/json',
                data: JSON.stringify(memberNo),
                success: function(data){
                    alert("탈퇴 처리 되었습니다.");
                    location.reload();
                },
                error: function(){
                    console.log("탈퇴 실패");
                }
            }); // ajax end
        } // if end
    });// click end
});