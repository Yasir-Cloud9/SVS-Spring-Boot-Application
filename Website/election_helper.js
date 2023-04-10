function fn() {
    const form = document.querySelector('#elecform'); // select the form element
    const url = 'http://localhost:8087/v1/election'; // replace with your API endpoint URL

    form.addEventListener('submit', (event) => {
        event.preventDefault(); // prevent form submission

        // get form data as a FormData object
        const formData = new FormData(form);
        // send a POST request to the API endpoint with the form data as the request body
        
        fetch(url, {
                method: 'POST',
                body: JSON.stringify(Object.fromEntries(formData.entries())),
                headers: {
                    'Content-Type': 'application/json'
                },
            })
            .then(response => 
                {
                    alert("Voter Registered Successfully");
                    response.json()
                }) // parse the response as JSON
            .then(data => {
                console.log('Response from server:', data); // log the response data to the console
                // do something with the response data, such as update the UI
            })
            .catch(error => {
                console.error('Error:', error); // log any errors to the console
                // handle the error, such as displaying an error message to the user
            });

            document.getElementsByTagName("form")[0].reset();
    });
}

document.addEventListener('DOMContentLoaded', fn);