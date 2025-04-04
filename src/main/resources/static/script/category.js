document.addEventListener("DOMContentLoaded", function () {
    // loadCategories();
});

// function loadCategories() {
//     let categories = [
//     ];

//     let table = document.getElementById("categoryTable");
//     table.innerHTML = "";

//     categories.forEach((cat, index) => {
//         let row = `<tr>
//             <td>${cat.name}</td>
//             <td>${cat.description}</td>
//             <td>${cat.image}</td>
//             <td>${cat.status}</td>
//             <td>
//                 <button onclick="editCategory(${index})">✏️ Edit</button>
//                 <button onclick="deleteCategory(${index})" style="color:red;">🗑️ Delete</button>
//             </td>
//         </tr>`;
//         table.innerHTML += row;
//     });
// }

function searchCategory() {
    let input = document.getElementById("searchBox").value.toLowerCase();
    let rows = document.querySelectorAll("#categoryTable tr");

    rows.forEach(row => {
        let text = row.innerText.toLowerCase();
        row.style.display = text.includes(input) ? "" : "none";
    });
}

let editIndex = null;

async function openModal(categoryId = null) {
    document.getElementById("categoryModal").style.display = "block";
    if (categoryId) {
        const response = await fetch(`/admin/categories/${categoryId}`).catch((err) =>
            console.error(err)
        );

        const category = await response.json();
        updateFields(category);
    }
}

function updateFields(category) {
    document.getElementById('categoryName').value = category.categoryTitle;
    document.getElementById('categoryDesc').value = category.categoryDescription;
    document.getElementById('categoryStatus').value = category.categoryStatus;
    document.getElementById('categoryId').value = category.categoryId;
    
    document.getElementById('imageUrl').value = category.categoryImageUrl;
}

function closeModal() {
    document.getElementById("categoryModal").style.display = "none";
}

// function saveCategory() {
//     let name = document.getElementById("categoryName").value;
//     let desc = document.getElementById("categoryDesc").value;
//     let image = document.getElementById("categoryImage").files[0]?.name || "📷";
//     let status = document.getElementById("categoryStatus").value;

//     if (name.trim() === "" || desc.trim() === "") {
//         alert("Please fill in all fields.");
//         return;
//     }

//     let table = document.getElementById("categoryTable");
//     let row = `<tr>
//         <td>${name}</td>
//         <td>${desc}</td>
//         <td>${image}</td>
//         <td>${status}</td>
//         <td>
//             <button onclick="editCategory()">✏️ Edit</button>
//             <button onclick="deleteCategory()" style="color:red;">🗑️ Delete</button>
//         </td>
//     </tr>`;

//     table.innerHTML += row;
//     closeModal();
// }

function editCategory(index) {
    alert("Edit Category " + index);
}

function deleteCategory(index) {
    alert("Delete Category " + index);
}
