const URL_HOST = 'http://localhost:9090';

async function getUserById(id) {
    try {
        const response = await fetch(URL_HOST + `/users/${id}`);
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Fetch error:', error);
        throw error;
    }
}

