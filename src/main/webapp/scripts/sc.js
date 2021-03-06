let items;

function sendAjax(request) {
    $.ajax(request).done(function(data) {
        let url = data.url;
        if (url === undefined) {
            document.getElementById('user').innerHTML = getParamURL() + " | Sign out";
            items = data.items;
            loadItems();
        } else {
            window.location.href = url;
        }
    }).fail(function(err){
        alert(err);
    });

}
function getItems() {
    let request = {
        type: 'GET',
        url: 'http://localhost:8080/todo/get',
        contentType: 'application/json;charset=UTF-8',
        dataType: 'json',
    };
    sendAjax(request);
    loadCategories();
}
function test() {
    alert($('#categories').val());
}
function newRow() {
    if (validate()) {
        let request = {
            type: 'POST',
            url: 'http://localhost:8080/todo/add',
            contentType: 'application/json;charset=UTF-8',
            dataType: 'json',
            data: JSON.stringify({"desc" : $("#desc").val(), "categories" : $('#categories').val()})
        };
        sendAjax(request);
    }

}

function updateStatus(id) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/todo/upd',
        contentType: 'application/json',
        data: JSON.stringify({"id" : id}),
    }).done(function(data) {

    }).fail(function(err){
        alert(err);
    });
}
function deleteItem(id) {
    let request = {
        type: 'POST',
        url: 'http://localhost:8080/todo/del',
        contentType: 'application/json;charset=UTF-8',
        dataType: 'json',
        data: JSON.stringify({"id" : id})
    };
    sendAjax(request);
}

function loadCategories() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/category',
        dataType: 'json'
    }).done(function(data) {
        let ctr = data.categories;
        for (let i = 0; i < ctr.length; i++) {
            $('#categories').append('<option value="' + ctr[i].id + '">' + ctr[i].name + '</option>');
        }
    }).fail(function(error) {
        alert(error)
    })
}

function loadItems() {
    $('#table > tbody').empty();
    let vl;
    for (let i = 0; i < items.length; i++) {
        vl = items[i];
        if ($('#done').prop("checked")) {
            rowAdd(vl.id, vl.description, vl.created, vl.user.name, vl.done);
        } else {
            if (!vl.done) {
                rowAdd(vl.id, vl.description, vl.created, vl.user.name, vl.done);
            }

        }
    }
}

function getIsDone() {
    getItems();
}

function rowAdd(id, desc, time, user, status) {
    let dateTime = time.split('T');
    $('#table > tbody:last-child')
        .append('<tr><td>' + id +'</td>'
            + '<td>' + desc +'</td>'
            + '<td>' + dateTime[0] + " "+ dateTime[1] +'</td>'
            + '<td>' + user +'</td>'
            + '<td>' +'<input type="checkbox" id="' + id +'" onclick="updateStatus('+ id +')"><br>'
            + '<button class="btn btn-info" type="submit" onclick="deleteItem('+ id +')">Delete</button></td><tr/>');
    $('#' + id).prop("checked", status);

}

function validate() {
    let result = true;
    let desc = $('#desc');
    if (desc.val() === "") {
        alert(desc.attr('title'));
        result = false;
    }
    return result;
}

function getParamURL() {
    return new URLSearchParams(window.location.search).get("value");
}