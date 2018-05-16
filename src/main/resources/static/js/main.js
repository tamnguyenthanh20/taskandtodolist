$(document).ready(function(){

    var date_input=$('input[name="todoDeadline"]');
    var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
    date_input.datepicker({
        format: 'yyyy/mm/dd',
        container: container,
        todayHighlight: true,
        autoclose: true
    });

    $('[data-toggle="tooltip"]').tooltip();
    var actions = $("#tasks td:last-child").html();

    // Append #tasks with add row form on add new button click
    $(".add-new").click(function(){
        $(this).attr("disabled", "disabled");
        var index = $("#tasks tbody tr:last-child").index();
        var row = $("#template").find("tr").clone();
        $("#tasks").append(row);
        $("#tasks tbody tr").eq(index + 1).find(".add, .edit").toggle();
        $('[data-toggle="tooltip"]').tooltip();
    });

    // Add row on add button click
    $(document).on("click", ".add", function(){
        var empty = false;
        var parent = $(this).parents("tr");
        var input = parent.find('input[type="text"]');
        input.each(function(){
            if(!$(this).val()){
                $(this).addClass("error");
                empty = true;
            } else{
                $(this).removeClass("error");
            }
        });
        parent.find(".error").first().focus();
        if(!empty){
            saveTask(parent.find(".task-name").val(), parent.find(".task-desc").val(), "ADD");
            input.each(function(){
                $(this).parent("td").html($(this).val());
            });
            parent.find(".add, .edit").toggle();
            $(".add-new").removeAttr("disabled");
        }
    });

    // Edit row on edit button click
    $(document).on("click", ".edit", function(){
        $(this).parents("tr").find("td:not(:last-child)").each(function(){
            $(this).html('<input type="text" class="' + $(this).className + '" value="' + $(this).text() + '">');
        });
        $(this).parents("tr").find(".add, .edit").toggle();
        $(".add-new").attr("disabled", "disabled");
    });

    // Delete row on delete button click
    $(document).on("click", ".delete", function(){
        $(this).parents("tr").remove();
        $(".add-new").removeAttr("disabled");
        var parent = $(this).parents("tr");
        saveTask(parent.find(".task-name").val(), parent.find(".task-desc").val(), "DELETE");
    });

    var actionMessage = $("#actionMessage").text();
    if (actionMessage) {
        alert(actionMessage);
    }
});

function saveTask(taskName,taskDesc, action) {
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/user/saveTask",
        data : {"taskName" : taskName, "taskDesc" : taskDesc, "action" : action},
        dataType : 'json',
        timeout : 100000,
        success : function(data) {
            console.log("SUCCESS: ", data);
            display(data);
        },
        error : function(e) {
            console.log("ERROR: ", e);
            display(e);
        }
    });
}