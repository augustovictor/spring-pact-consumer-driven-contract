const http = require('http')

http.createServer(function (req, res) {
    if (req.url === '/external/users') {
        res.writeHead(200, {'Content-Type': 'application/json'});
        res.write("{ username: 'victoraweb', name: 'Victor Costa', phone: '11 9999-9999'}")
        res.end();
    } else {
        res.end()
    }
}).listen(3000);
console.log('Running on port 3000')