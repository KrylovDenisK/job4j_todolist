function auth() {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/todo/auth',
        contentType: 'application/json;charset=UTF-8',
        dataType: 'json',
        data: JSON.stringify({"login" : $('#login').val(), "password" : $('#password').val()})
    }).done(function (data) {
        let user = data.user;
        if (user === undefined) {
            alert("Пользователь не найден!!!");
        } else {
            window.location.href = '/todo/index.html?value=' + user;
        }
    }).fail(function (err) {
        alert(err);
    });
}

function redirectToReg() {
    window.location.href = '/todo/reg/reg.html';
}