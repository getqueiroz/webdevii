function fetchUserById() {
    const userId = document.getElementById('userIdToBeFetched').value;
    getUserById(userId).then(user => {
        if(user){
            document.getElementById('userId').textContent = user.id;
            document.getElementById('userName').textContent = user.username;
            document.getElementById('createdAt').textContent = user.createdAt;
            document.getElementById('updatedAt').textContent = user.updatedAt;
        } else {
            window.alert("Usuário não encontrado");
        }
    });
}