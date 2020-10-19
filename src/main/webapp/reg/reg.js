function registration() {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/todo/reg',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify({"login" : $('#login').val(),
            "email" : $('#email').val() ,"password" : $('#password').val()})
    }).done(function (data) {
        window.location.href = '/todo/index.html?value=' + data.user;
    }).fail(function (error) {
        alert(error);
    })
}