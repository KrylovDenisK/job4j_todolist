let items;

function sendAjax(request) {
    $.ajax(request).done(function(data) {
        items = data.items;
        loadItems();
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
}

function newRow() {
    if (validate()) {
        let request = {
            type: 'POST',
            url: 'http://localhost:8080/todo/add',
            contentType: 'application/json;charset=UTF-8',
            dataType: 'json',
            data: JSON.stringify({"desc" : $("#desc").val()})
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

function loadItems() {
    $('#table > tbody').empty();
    let vl;
     for (let i = 0; i < items.length; i++) {
         vl = items[i];
         if ($('#done').prop("checked")) {
             rowAdd(vl.id, vl.description, vl.created, vl.done);
         } else {
             if (!vl.done) {
                 rowAdd(vl.id, vl.description, vl.created, vl.done);
             }

         }
     }
}

function getIsDone() {
    getItems();
}

function rowAdd(id, desc, time, status) {
    $('#table > tbody:last-child')
        .append('<tr><td>' + id +'</td>'
            + '<td>' + desc +'</td>'
            + '<td>' + time +'</td>'
            + '<td>' +'<input type="checkbox" id="' + id +'" onclick="updateStatus('+ id +')">'
            + '<button class="form-control" type="submit" onclick="deleteItem('+ id +')">Delete</button></td><tr/>');
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
