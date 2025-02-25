document.addEventListener("DOMContentLoaded", function () {
    loadProducts();
});

// function loadProducts() {
//     let products = [];

//     let table = document.getElementById("productTable");
//     table.innerHTML = "";

//     products.forEach((prod, index) => {
//         let row = `<tr>
//             <td>${prod.name}</td>
//             <td>${prod.description}</td>
//             <td>${prod.image}</td>
//             <td>${prod.price}</td>
//             <td>${prod.stock}</td>
//             <td>
//                 <button onclick="editProduct(${index})">✏️ Edit</button>
//                 <button onclick="deleteProduct(${index})" style="color:red;">🗑️ Delete</button>
//             </td>
//         </tr>`;
//         table.innerHTML += row;
//     });
// }

function searchProduct() {
    let input = document.getElementById("searchBox").value.toLowerCase();
    let rows = document.querySelectorAll("#productTable tr");

    rows.forEach(row => {
        let text = row.innerText.toLowerCase();
        row.style.display = text.includes(input) ? "" : "none";
    });
}

function openModal() {
    document.getElementById("productModal").style.display = "flex";
}

function closeModal() {
    document.getElementById("productModal").style.display = "none";
}

function saveProduct(event) {
    event.preventDefault();
    let name = document.getElementById("productName").value;
    let desc = document.getElementById("productDesc").value;
    let image = document.getElementById("productImage").files[0]?.name || "📷";
    let price = document.getElementById("productPrice").value;
    let stock = document.getElementById("productStock").value;

    if (name.trim() === "" || desc.trim() === "" || price.trim() === "" || stock.trim() === "") {
        alert("Please fill in all fields.");
        return;
    }

    let table = document.getElementById("productTable");
    let row = `<tr>
        <td>${name}</td>
        <td>${desc}</td>
        <td>${image}</td>
        <td>${price}</td>
        <td>${stock}</td>
        <td>
            <button onclick="editProduct()">✏️ Edit</button>
            <button onclick="deleteProduct()" style="color:red;">🗑️ Delete</button>
        </td>
    </tr>`;

    table.innerHTML += row;
    closeModal();
}

document.getElementById("product").addEventListener("submit", saveProduct);

function editProduct(index) {
    alert("Edit Product " + index);
}

function deleteProduct(index) {
    alert("Delete Product " + index);
}
