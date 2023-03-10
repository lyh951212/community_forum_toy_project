// 현재 버튼 id
var cur_selected_btn_id = "ALL";

function tab_click(id) {

    if(id ==  cur_selected_btn_id)
        return;

    var prev_elem = document.getElementById(cur_selected_btn_id);
    prev_elem.classList.remove("active");

    var elem = document.getElementById(id);
    elem.classList.add("active");

    cur_selected_btn_id = id;

    $.ajax({
        type: "POST",
        url: "/faq",
        data :{ faqTypeStr : id },
        dataType : "text",
        success: function (result) {
            $("#FaqListContentByType").replaceWith(result);
        },
        fail: function(jqXHR) {
            alert("fail");
            console.log(jqXHR);
        },
    })
}

function accordion_click(accordion){

    if(accordion.classList.contains("collapsed"))
        accordion.classList.remove("collapsed");
    else
        accordion.classList.add("collapsed");

    // 부모 : parentNode
    const parentElem = accordion.parentElement;
    if(parentElem.nextElementSibling.classList.contains("show"))
        parentElem.nextElementSibling.classList.remove("show");
    else
        parentElem.nextElementSibling.classList.add("show");

}

// 현재 버튼 id
var prev_selected_typd_id = "btnradio1";
var cur_selected_typd_id = "btnradio1";
var selected_type = 1;

function select_type(id) {
    if(id ==  prev_selected_typd_id)
        return;

    document.getElementById(prev_selected_typd_id).removeAttribute("th:name");
    var numStr = id.substr("btnradio".length);
    selected_type = Number(numStr)-1;
    document.getElementById(id).setAttribute("th:name", "type");
    document.getElementById(id).setAttribute("th:value", selected_type);
    prev_selected_typd_id = cur_selected_typd_id;
    cur_selected_typd_id = id;
}

function register_faq(){
    var $form = $('#register-form');

    $.ajax({
        url: $form.attr('action'),
        type: $form.attr('method'),
        data: {
            type: $form.find(".btn-check:checked").val(),
            title: $form.find('input[name=title]').val(),
            content: $form.find('input[name=content]').val(),
        },
        success: function (data) {
            if("redirect:/faq" == data)
                alert("저장되었습니다.");

            location.href = data;
        },
        error: function (data) {
            console.log(data);
            alert("저장 실패");
        }
    })
}