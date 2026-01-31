const API_URL = "http://localhost:8080/library/book";

// 1. Fetch all books from Backend
function loadBooks() {
    fetch(API_URL)
        .then(res => {
            if (!res.ok) throw new Error("Network response was not ok");
            return res.json();
        })
        .then(result => {
            const tableBody = document.getElementById("bookTable");
            tableBody.innerHTML = "";

            // Accessing 'data' field from your ResponseStructure
            const books = result.data || [];

            if (books.length === 0) {
                tableBody.innerHTML = `<tr><td colspan="5" style="text-align:center;">No books found.</td></tr>`;
                return;
            }

            books.forEach(book => {
                tableBody.innerHTML += `
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>₹${book.price}</td>
                        <td>
                            <button onclick="deleteBook(${book.id})" class="delete-btn">Delete</button>
                        </td>
                    </tr>
                `;
            });
        })
        .catch(err => {
            console.error("Error:", err);
            // This alert shows if Spring Boot is down or CORS is missing
            alert("Connection Error. Check if Spring Boot is running.");
        });
}

// 2. Form Submission (Add Book)
document.getElementById("bookForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const bookData = {
        title: document.getElementById("title").value,
        author: document.getElementById("author").value,
        price: parseFloat(document.getElementById("price").value),
        publishedYear: document.getElementById("publishedYear").value,
        genre: document.getElementById("genre").value
    };

    fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(bookData)
    })
    .then(res => res.json())
    .then(result => {
        alert(result.message || "Book Added!");
        document.getElementById("bookForm").reset(); // Clear form after success
        loadBooks(); // Refresh table
    })
    .catch(err => alert("Failed to add book. Check console."));
});

// 3. Reset Button Logic
document.getElementById("resetBtn").addEventListener("click", function() {
    document.getElementById("bookForm").reset();
    console.log("Form reset by user.");
});

// 4. Delete Single Book
function deleteBook(id) {
    if (confirm("Are you sure you want to delete this book?")) {
        fetch(`${API_URL}/${id}`, { method: "DELETE" })
            .then(res => res.json())
            .then(result => {
                alert(result.message);
                loadBooks();
            });
    }
}

// 5. Delete All Books
function deleteAll() {
    if (confirm("DANGER: This will delete ALL books! Proceed?")) {
        fetch(`${API_URL}/all`, { method: "DELETE" })
            .then(res => res.json())
            .then(result => {
                alert(result.message);
                loadBooks();
            });
    }
}

// Load data automatically when page opens
window.onload = loadBooks;