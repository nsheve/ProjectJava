"use strict"

async function myregister(e) {

    await fetch('/registration/singing', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: user
    }).then(() => {
        console.log(`success!`);
    })

    const form = document.querySelector("#regForm");

    const user = await JSON.stringify({
        username: form.username.value.trim(),
        password: form.password.value,
        roles: ['USER']
    })
}