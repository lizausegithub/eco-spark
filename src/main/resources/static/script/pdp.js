// Change Main Image
function changeImage(element) {
    let mainImage = document.getElementById("mainImage");
    mainImage.src = element.src;
}

// Toggle Mobile Menu
function toggleMenu() {
    document.querySelector("nav").classList.toggle("menu-open");
}

// Add to Cart Alert
document.querySelector(".cart-btn").addEventListener("click", function () {
    alert("Item added to cart!");
});

// Buy Now Alert
document.querySelector(".buy-btn").addEventListener("click", function () {
    alert("Proceeding to checkout...");
});

//review
function submitReview() {
    const name = document.getElementById("name").value;
    const text = document.getElementById("review-text").value;
    const rating = document.getElementById("rating").value;

    if (name === "" || text === "") {
        alert("Please enter your name and review.");
        return;
    }

    const reviewSection = document.querySelector(".reviews");
    const newReview = document.createElement("div");
    newReview.classList.add("review-item");
    newReview.innerHTML = `
        <div class="review-header">
            <span class="review-user">${name}</span>
            <span class="review-date">${new Date().toDateString()}</span>
        </div>
        <div class="review-stars">${"★".repeat(rating) + "☆".repeat(5 - rating)}</div>
        <p class="review-text">${text}</p>
    `;

    reviewSection.appendChild(newReview);
    document.getElementById("name").value = "";
    document.getElementById("review-text").value = "";
}
