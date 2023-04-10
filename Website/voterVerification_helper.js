function voterVerification_helper() {
    const form = document.querySelector('#vvform');
    const submitButton = document.querySelector('#fetch-btn');
    const apiUrl = 'http://localhost:8085/v1/voter'; // replace with your API endpoint
    submitButton.addEventListener('click', (event) => {
        event.preventDefault(); // prevent form submission

        const voterId = document.getElementById('voter-id').value; // get the voterId from input field
        const url = apiUrl + "/" + voterId;


        fetch(url)
            .then(response => {
                // If the response is not OK (i.e. 200 status code), throw an error
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                // Otherwise, parse the response body as JSON and return it
                return response.json();
            })
            .then(data => {
                // Once the data is retrieved, update the HTML page with the values
                // update the HTML elements with voter details
                document.getElementById('voter_ID').textContent = data.voterID;
                document.getElementById('name').textContent = data.voterName;
                document.getElementById('gender').textContent = data.voterGender;
                document.getElementById('parent-name').textContent = data.voterParentName;
                document.getElementById('place-of-birth').textContent = data.voterPlaceOfBirth;
                document.getElementById('date-of-birth').textContent = data.voterDateOfBirth;

                // Check if voter has already voted
                if (data.voterVoted) {
                    alert('You have already voted!');
                    location.reload();
                }
                
                //**Verification for face start from here */
                var voterPhotos ={
                    photo1: data.voterID + ".jpg",
                    photo2: data.voterID + "_" + data.voterID + ".jpg"
                }

                const apiUrl_election = 'http://localhost:8088/v1/compare-faces';
                fetch(apiUrl_election, {
                    method: 'POST',
                    body: JSON.stringify(voterPhotos),
                    headers: {
                        'Content-Type': 'application/json'
                    },
                })
                .then(response => 
                    {

                          // If the response is not OK (i.e. 200 status code), throw an error
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                // Otherwise, parse the response body as JSON and return it
                return response.text();

                       
                    }) // parse the response as JSON
                .then(data => {
                    if( data=="False" || data=="Error")
                    {
                        alert("Photo identification failed.");
                        location.reload();
                    }
                    console.log('Response from server:', data); // log the response data to the console
                    // do something with the response data, such as update the UI
                })


            })
            .catch(error => {
                // If there was an error making the request or parsing the response, display an error message
                console.error('Error:', error);
            });
    })
}
document.addEventListener('DOMContentLoaded', voterVerification_helper);