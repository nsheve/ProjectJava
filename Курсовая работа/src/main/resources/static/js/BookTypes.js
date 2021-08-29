"use strict"

let d = document;

async function addRow()
{
    let json = await JSON.stringify({
        name : d.getElementById('Name').value,
        cnt : d.getElementById('Cnt').value ,
        fine : d.getElementById('Fine').value,
        dayCount : d.getElementById('DayCount').value
    });

    const response = await fetch('/library/addBookType', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: json
    });

    const newBookType = await response.json();

    if (response.ok) {
        let tbody = d.getElementById('table').getElementsByTagName('TBODY')[0];
        let row = d.createElement("TR");
        tbody.appendChild(row);

        let td1 = d.createElement("TD");
        let td2 = d.createElement("TD");
        let td3 = d.createElement("TD");
        let td4 = d.createElement("TD");
        let td5 = d.createElement("TD");

        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);

        td1.innerHTML = newBookType.id;
        td2.innerHTML = newBookType.name;
        td3.innerHTML = newBookType.cnt;
        td4.innerHTML = newBookType.fine;
        td5.innerHTML = newBookType.dayCount;

        d.getElementById('Name').value= "";
        d.getElementById('Cnt').value= "";
        d.getElementById('Fine').value= "";
        d.getElementById('DayCount').value= "";

        bookTypes.push(newBookType);
        renderBookTypesSelectAdd();
        renderBookTypesSelectUpdate();
    } else {
        alert("ERROR adding this Book type")
    }
}

async function getBookType() {
    let bookTypeId = document.getElementById('idForSearch').value;
    let urlString = "/library/bookType/" + bookTypeId;
    const response = await fetch(urlString);

    if (response.ok) {
        const bookType = await response.json();
        alert("BookType name = " + bookType.name + " BookType cnt = " + bookType.cnt + " BookType fine = " + bookType.fine + " BookType day count = "+ bookType.dayCount);
    } else {
        alert("BookType with id " + bookTypeId + " not Found")
    }
}

async function deleteBookType() {
    if (confirm("Вы действительно хотите удалить?")) {
        let bookTypeId = document.getElementById('idForDelete').value;
        let table = document.getElementById("table");

        let urlString = "/library/deleteBookType/" + bookTypeId;
        const response = await fetch(urlString, {
            method: 'DELETE'
        });

        if (response.ok) {
            $('table tr').each(async function (row) {
                if ($($(this).find('td')[0]).text() === bookTypeId) {
                    table.deleteRow(row);
                    return;
                }
            })
        } else {
            alert("Book type with id " + bookTypeId + " NOT FOUND")
        }
    }
}

async function updateBookType() {
    let bookTypeId = document.getElementById('IdOfUpdatedBookType').value;
    let urlString = "/library/updateBookType/" + bookTypeId;

    let newName = d.getElementById("NameToUpdate").value;
    let newCnt = d.getElementById("CntToUpdate").value;
    let newFine = d.getElementById("FineToUpdate").value;
    let newDayCount = d.getElementById("DayCountToUpdate").value;

    let json = await JSON.stringify({
        name: newName,
        cnt: newCnt,
        fine: newFine,
        dayCount: newDayCount
    });

    const response = await fetch(urlString, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: json
    });

    if (response.ok) {
        $('table tr').each(async function (row) {
            if ($($(this).find('td')[0]).text() === bookTypeId) {
                let rowToUpdate = d.getElementById("table").getElementsByTagName('TR')[row].getElementsByTagName('TD');
                rowToUpdate[1].innerHTML = newName;
                rowToUpdate[2].innerHTML = newCnt;
                rowToUpdate[3].innerHTML = newFine;
                rowToUpdate[4].innerHTML = newDayCount;
                return;
            }
        })
    } else {
        alert("Book type with id" + bookTypeId + "NOT FOUND");
    }
}

