$(document).ready(function() {
    // Function to fetch and display paintings
    function fetchAndDisplayPaintings() {
        $.ajax({
            url: '/festoi/art',
            type: 'GET',
            dataType: 'json',
            success: function(paintings) {
                const paintingsContainer = $('#paintings-container');
                paintingsContainer.empty(); // Clear previous content

                paintings.forEach(function(painting) {
                    const paintingElement = createPaintingElement(painting);
                    paintingsContainer.append(paintingElement);
                });
            },
            error: function(error) {
                console.error('Error fetching paintings:', error);
            }
        });
    }

    // Function to create a painting element
    function createPaintingElement(painting) {
        const paintingElement = `
            <div class="painting">
                <img src="${painting.url}" alt="${painting.artName}">
                <h2 class="painting-title">${painting.artName}</h2>
                <p class="painting-artist">${painting.artistName}</p>
                <p class="painting-price">${painting.price} HUF</p>
                <button class="like-button" data-art-name="${painting.artName}" data-is-liked="false">${painting.likesNumber}</button>
            </div>
        `;

        return paintingElement;
    }

    // Check if the user is an artist
    $.ajax({
        url: '/festoi/artists/check',
        type: 'GET',
        success: function(response) {
            // Check the response to determine if the user is an artist
            if (response === true) {
                // User is an artist, dynamically add the new <a> tag to the <nav> section
                var newLink = $('<a href="/festoi/upload-art">Upload Art</a>');
                $('#main-nav').append(newLink);
            }

            // Fetch and display paintings for all users
            fetchAndDisplayPaintings();
        },
        error: function(error) {
            console.error('Error fetching user data:', error);

            // Fetch and display paintings for all users immediately if there's an error
            fetchAndDisplayPaintings();
        }
    });

    // JavaScript to toggle the active class on the like button
    $(document).on('click', '.like-button', function() {
        const likeButton = $(this);
        let isLiked = likeButton.data('is-liked');
        const artName = likeButton.data('art-name');

        // Toggle the isLiked value
        isLiked = !isLiked;

        likeButton.data('is-liked', isLiked); // Update the data attribute
        updateLikes(artName, isLiked);
    });

    // Function to update likes
    function updateLikes(artName, isLiked) {
        $.ajax({
            url: `/festoi/art/update-likes/${artName}?isLiked=${isLiked}`,
            type: 'POST',
            success: function(updatedPainting) {
                // Handle the updated painting (if needed)

                // Assuming you want to update the likes count on the button
                const likeButton = $(`button[data-art-name="${updatedPainting.artName}"]`);
                likeButton.text(updatedPainting.likesNumber);
            },
            error: function(error) {
                console.error('Error updating likes:', error);
            }
        });
    }
});
