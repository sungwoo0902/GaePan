$(function(){
    // checkbox 전체 선택
    $("#selectAll").click(function(){
        if($("#selectAll").is(":checked")){
            $(".checkbox").prop("checked", true);
        }else{
            $(".checkbox").prop("checked", false);
        }
    });

    // checkbox 개별 선택
    $(".checkbox").click(function(){
        let checkbox = $(".checkbox").length;
        let checked = $(".checkbox:checked").length;

        if(checkbox != checked){
            $("#selectAll").prop("checked", false);
        }else{
            $("#selectAll").prop("checked", true);
        }
    });
});