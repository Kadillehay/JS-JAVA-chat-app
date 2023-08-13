document.addEventListener("DOMContentLoaded", function() {
	var name = sessionStorage.getItem("userName");

	if (name) {
		alert("Welcome back, " + name + "!");
	} else {
		name = prompt("Please enter your name", "John Doe");
		if (name) {

			sessionStorage.setItem("userName", name);
			alert("Welcome, " + name + "!");
		} else {
			alert("Welcome!");
		}
	}

	var createChannelForm = document.getElementById("create-channel-form");
	if (createChannelForm) {
		createChannelForm.addEventListener("submit", function(event) {
			event.preventDefault();
			var channelName = document.getElementById("channelName").value;
			if (channelName) {
				fetch("/createChannel", {
					method: "POST",
					headers: {
						"Content-Type": "application/x-www-form-urlencoded",
					},
					body: "channelName=" + encodeURIComponent(channelName),
				})
					.then(function(response) {
						if (response.ok) {
							window.location.href = "/channels/" + encodeURIComponent(channelName);
						} else {
							alert("Failed to create channel.");
						}
					})
					.catch(function(error) {
						console.error("Error creating channel:", error);
						alert("An error occurred while creating the channel.");
					});
			}
		});
	}


});
