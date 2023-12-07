$(document).ready(function() {

    const cateUrl = "/admin/board/ajaxCate"
    const typeUrl = "/admin/board/ajaxType"

    $("#boardGroup").change(function() {
        loadCategories();
    });

    function loadCategories() {
        let group = $("#boardGroup").val();
        console.log(group);
        $.post(cateUrl, { group: group }, function(data) {
            $('#cate').empty();
            $.each(data, function(index, cate) {
                $('#cate').append('<option required value="' + cate.cate + '">' + cate.cateName + '</option>');
            });
            // 카테고리가 변경되면 타입도 업데이트
            $("#cate").change(function (){
                loadTypes();
            });
        }); // ajax end
    } // loadCategories end

    // 초기화
    loadCategories();

    function loadTypes() {
        let cate = $("#cate").val();
        console.log(cate);
        $.post(typeUrl, { cate: cate }, function(data) {
            $('#type').empty();
            $.each(data, function(index, type) {
                $('#type').append('<option required value="' + type.type + '">' + type.typeName + '</option>');
            });
        }); // ajax end
    } // loadTypes end
});