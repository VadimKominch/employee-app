const container = document.querySelector(".container");
const searchBox = document.querySelector(".search-input");
const searchBlock = document.querySelector(".search-results");
const addButton = document.getElementsByClassName("create")[0];
const getAllButton = document.getElementsByClassName("show")[0];
const shadow = document.getElementsByClassName("shadow")[0];

async function loadAll() {
    const employees = await fetch("http://localhost:8080/simplewebapp/all");
    const jsonData = await employees.text();
    const employeeArray = await JSON.parse(jsonData);
    console.log(employeeArray);
    container.innerHTML = "";
    employeeArray.forEach(el => {
        container.appendChild(createEmployeeCard(el));
    });
}

async function getOne(e) {
    const id = e.target.value;
    const employee = await fetch(`http://localhost:8080/simplewebapp/one/${id}`);
    const jsonData = await employee.text();
    if(!jsonData) {
       throw new Error();
    } else {
        return jsonData;
    }
}

function save(e) {
    const card = e.target.parentNode;
    const body = {
        "firstName":card.getElementsByClassName("input")[0].value,
        "lastName" :card.getElementsByClassName("input")[1].value,
        "jobTitle" :card.getElementsByClassName("input")[2].value,
        "department":card.getElementsByClassName("input")[3].value,
        "gender":"male",
        "dateOfBirth":card.getElementsByClassName("input")[4].value
    };
    console.log(JSON.stringify(body));
//save request. close window after it ends up
    fetch("http://localhost:8080/simplewebapp/add",{
                method: "POST",    
                headers: {
                    "Content-Type": "application/json"
                  },
                  body: JSON.stringify(body)
            })
            .then(res=>console.log("saved"));
}

function edit(e) {
    const card = e.target.parentNode;
    const body = {
        "firstName":card.getElementsByClassName("first")[0].innerText,
        "lastName" :card.getElementsByClassName("last")[0].innerText,
        "jobTitle" :card.getElementsByClassName("job")[0].innerText,
        "gender" :card.getElementsByClassName("gender")[0].innerText || "male",
        "department":card.getElementsByClassName("department")[0].innerText,
        "dateOfBirth":card.getElementsByClassName("date")[0].innerText
    };
    const id = card.querySelector(".id").innerText;
    console.log(body);
//update request. close window after it ends up
    fetch(`http://localhost:8080/simplewebapp/update/id/${id}`,{
                method: "POST",    
                headers: {
                    "Content-Type": "application/json"
                  },
                  body: JSON.stringify(body)
            })
            .then(res=>console.log("updated"));
}

function remove(e) {
    const card = e.target.parentNode;
    console.log(card);
    const id = card.querySelector(".id").innerText; // get id of removed employee
    fetch(`http://localhost:8080/simplewebapp/delete/id/${id}`,{
                method: "DELETE",    
                headers: {},
            })
            .then(res=>card.remove());
}

function createElement(elementName,classList) {
    const element = document.createElement(elementName);
    if(classList)
    classList.forEach(el=> element.classList.add(el));
    return element;
}

function additionForm() {
    shadow.style.visibility = "visible";
}

function createEmployeeCard(obj) {
    const div = createElement("div",["card"]);
    const id = createElement("input");
    id.setAttribute("type", "hidden");
    id.classList.add("id"); 
    id.innerHTML = obj["employeeId"];
    div.appendChild(id);
    const image = createElement("img",["img"]);
    image.src = "image.png"; // add png to folder
    image.alt = " avatar";  // change to blob in java
    div.appendChild(image);
    const wrapper = createElement("div",["full-name"]);
    const firstName = createElement("h3",["first"]);
    firstName.innerHTML = obj["firstName"];
    firstName.contentEditable = true;
    wrapper.appendChild(firstName);
    
    const lastName = createElement("h3",["last"]);
    lastName.innerHTML = obj["lastName"];
    lastName.contentEditable  = true;
    wrapper.appendChild(lastName);
    div.appendChild(wrapper);
    
    const gender = createElement("h3",["gender"]);
    gender.innerHTML = obj["gender"];
    gender.contentEditable  = true;
    div.appendChild(gender);

    const jobTitle = createElement("h3",["job"]);
    jobTitle.innerHTML = obj["jobTitle"];
    jobTitle.contentEditable  = true;
    div.appendChild(jobTitle);
    
    const department = createElement("h3",["department"]);
    department.innerHTML = obj["department"];
    department.contentEditable  = true;
    div.appendChild(department);
    
    const dateOfBirth = createElement("h3",["date"]);
    dateOfBirth.innerHTML = obj["dateOfBirth"];
    dateOfBirth.contentEditable  = true;
    div.appendChild(dateOfBirth);
    
    const changeButton = createElement("button",["btn","edit"]);
    changeButton.classList.add("btn");
    changeButton.innerHTML = "Edit";
    changeButton.addEventListener("click",edit);
    div.appendChild(changeButton);

    const deleteButton = createElement("button",["btn","delete"]);
    deleteButton.innerHTML = "Delete";
    deleteButton.addEventListener("click",remove);
    div.appendChild(deleteButton);
    return div;
}

function appendResult(res) {
    const element = createEmployeeCard(res);
    searchBlock.innerHTML = "";
    container.innerHTML = element.outerHTML;
}

addButton.addEventListener("click",additionForm);
searchBox.addEventListener("input",function(e) {
    if(!e.target.value) {
        loadAll();
        return;
    }
    const jsonData = getOne(e);
    if(!jsonData) return;
    jsonData
    .then(res=>JSON.parse(res))
    .then(json=>{
        console.log(json);
        appendResult(json);
    }).catch(error=>{
        container.innerHTML = "";
    });
});
getAllButton.addEventListener("click",loadAll);
shadow.addEventListener("click",(e)=>{
    if(!e.target.classList.contains("add-employee")) {
        shadow.style.visibility = "hidden";
    }
});

document.addEventListener("DOMContentLoaded", loadAll);