"use strict"

let d = document;

async function addRow() {
    let json = await JSON.stringify({
        bookId   : d.getElementById('BookId').value,
        clientId : d.getElementById('ClientId').value,
        dateBeg  : d.getElementById('DateBeg').value,
        dateEnd  : d.getElementById('DateEnd').value,
        dateRet  : d.getElementById('DateRet').value
    });

    const response = await fetch('/library/addJournal', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: json
    });
    const newJournal = await response.json();

    if (response.ok) {

        let tbody = d.getElementById('table').getElementsByTagName('TBODY')[0];

        let row = d.createElement("TR");
        tbody.appendChild(row);

        let td1 = d.createElement("TD");
        let td2 = d.createElement("TD");
        let td3 = d.createElement("TD");
        let td4 = d.createElement("TD");
        let td5 = d.createElement("TD");
        let td6 = d.createElement("TD");

        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        row.appendChild(td6);

        td1.innerHTML = newJournal.id;
        td2.innerHTML = newJournal.bookId;
        td3.innerHTML = newJournal.clientId;
        td4.innerHTML = newJournal.dateBeg;
        td5.innerHTML = newJournal.dateEnd;
        td6.innerHTML = newJournal.dateRet;

        d.getElementById('BookId').value = "";
        d.getElementById('ClientId').value = "";
        d.getElementById('DateBeg').value = "";
        d.getElementById('DateEnd').value = "";
        d.getElementById('DateRet').value = "";

    } else {
        alert("ERROR adding this journal record or Book or Client does not exist")
    }
}

async function getJournal() {
    let JournalId = document.getElementById('idForSearch').value;
    let urlString = "/library/journal/" + JournalId;
    const response = await fetch(urlString);

    if (response.ok) {
        const journal = await response.json();
        alert("bookId = " + journal.bookId + " clientId = " + journal.clientId + " DateBeg = " +
            Date.parse(journal.dateBeg) +" DateEnd = " + Date.parse(journal.dateEnd) +"  " +
            "DateRet = " + Date.parse(journal.dateRet));
    } else {
        alert("Journal with id " + JournalId + " not Found")
    }
}

async function deleteJournal() {
    if (confirm("Вы действительно хотите удалить?")) {
        let journalId = document.getElementById('idForDelete').value;
        let table = document.getElementById("table");

        let urlString = "/library/deleteJournal/" + journalId;
        const response = await fetch(urlString, {
            method: 'DELETE'
        });

        if (response.ok) {
            $('table tr').each(async function (row) {
                if ($($(this).find('td')[0]).text() === journalId) {
                    table.deleteRow(row);
                    return;
                }
            })
        } else {
            alert("Journal with id " + journalId + " not Found")
        }
    }
}


async function updateJournal() {
    let journalID = document.getElementById('IdOfUpdatedJournal').value;
    let urlString = "/library/updateJournal/" + journalID;

    let newBookId   = d.getElementById("BookIdToUpdate").value;
    let newClientId = d.getElementById("ClientIdToUpdate").value;
    let newDateBeg  = d.getElementById("DateBegToUpdate").value;
    let newDateEnd  = d.getElementById("DateEndToUpdate").value;
    let newDateRet  = d.getElementById("DateRetToUpdate").value;

    let json = await JSON.stringify({
        bookId:   newBookId,
        clientId: newClientId,
        dateBeg:  newDateBeg,
        dateEnd:  newDateEnd,
        dateRet:  newDateRet
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
            if ($($(this).find('td')[0]).text() === journalID) {
                let rowToUpdate = d.getElementById("table").getElementsByTagName('TR')[row].getElementsByTagName('TD');
                rowToUpdate[1].innerHTML = newBookId;
                rowToUpdate[2].innerHTML = newClientId;
                rowToUpdate[3].innerHTML = newDateBeg;
                rowToUpdate[4].innerHTML = newDateEnd;
                rowToUpdate[5].innerHTML = newDateRet;
                return;
            }
        })
    } else {
        alert("Journal with id " + journalID + " not found or Book or Client does not exist");
    }
}