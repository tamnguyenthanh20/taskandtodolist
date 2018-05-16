$(document).ready(function(){


    // $('[data-toggle="tooltip"]').tooltip();
    // var actions = $("#tasks td:last-child").html();
    //
    // // Append #tasks with add row form on add new button click
    // $(".add-new").click(function(){
    //     $(this).attr("disabled", "disabled");
    //     var index = $("#tasks tbody tr:last-child").index();
    //     var row = $("#template").find("tr").clone();
    //     $("#tasks").append(row);
    //     $("#tasks tbody tr").eq(index + 1).find(".add, .edit").toggle();
    //     $('[data-toggle="tooltip"]').tooltip();
    // });
    //
    // // Add row on add button click
    // $(document).on("click", ".add", function(){
    //     var empty = false;
    //     var parent = $(this).parents("tr");
    //     var input = parent.find('input[type="text"]');
    //     input.each(function(){
    //         if(!$(this).val()){
    //             $(this).addClass("error");
    //             empty = true;
    //         } else{
    //             $(this).removeClass("error");
    //         }
    //     });
    //     parent.find(".error").first().focus();
    //     if(!empty){
    //         saveTodo(parent.find(".todo-id").val(), parent.find(".todo-name").val(),
    //             parent.find(".todo-deadline").val(), parent.find(".todo-complete").val());
    //
    //         input.each(function(){
    //             $(this).parent("td").html($(this).val());
    //         });
    //         parent.find(".add, .edit").toggle();
    //         $(".add-new").removeAttr("disabled");
    //     }
    // });
    //
    // // Edit row on edit button click
    // $(document).on("click", ".edit", function(){
    //     $(this).parents("tr").find("td:not(:last-child)").each(function(){
    //         $(this).html('<input type="text" class="' + $(this).className + '" value="' + $(this).text() + '">');
    //     });
    //     $(this).parents("tr").find(".add, .edit").toggle();
    //     $(".add-new").attr("disabled", "disabled");
    // });
    //
    // // Delete row on delete button click
    // $(document).on("click", ".delete", function(){
    //     $(".add-new").removeAttr("disabled");
    //     var parent = $(this).parents("tr");
    //     deleteTodo(parent.find(".todo-id").text());
    //     $(this).parents("tr").remove();
    // });
    //
    // var actionMessage = $("#actionMessage").text();
    // if (actionMessage) {
    //     alert(actionMessage);
    // }
});

// function saveTodo(id, name, deadline, complete) {
//     $.ajax({
//         type : "POST",
//         contentType : "application/json",
//         url : "/user/saveTodo",
//         data : {"id" : id, "name" : name, "deadline" : deadline, "complete" : complete},
//         dataType : 'json',
//         timeout : 100000,
//         success : function(data) {
//             console.log("SUCCESS: ", data);
//         },
//         error : function(e) {
//             console.log("ERROR: ", e);
//         }
//     });
// }
//
// function deleteTodo(todoid) {
//     $.ajax({
//         type : "POST",
//         contentType : "application/json",
//         url : "/user/deleteTodo",
//         data : {"todoid" : parseFloat(todoid)},
//         dataType : 'json',
//         timeout : 100000,
//         success : function(data) {
//             console.log("SUCCESS: ", data);
//         },
//         error : function(e) {
//             console.log("ERROR: ", e);
//         }
//     });
// }