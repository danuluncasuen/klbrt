// Connect
const socket = new SockJS('http://localhost:8080/ws');
const stompClient = Stomp.over(socket);

stompClient.connect({}, function(frame) {
    console.log('Connected');
    
    // Subscribe to document changes
    stompClient.subscribe('/topic/document/123', function(message) {
        const change = JSON.parse(message.body);
        document.getElementById('editor').value = change.content;
    });
});

// Send changes
function sendEdit() {
    const content = document.getElementById('editor').value;
    stompClient.send('/app/document/123/edit', {}, 
        JSON.stringify({ content: content, version: 1 }));
}