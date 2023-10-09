const express = require('express');
const path = require('path');
const app = express();
const cors = require('cors');
const { createProxyMiddleware } = require("http-proxy-middleware");
app.use(express.json());
app.use(cors());

app.use(express.static(path.join(__dirname, './build')));

app.use(
    "/search",
    createProxyMiddleware({
        target: "https://search.street-drop.com",
        changeOrigin: true,
        pathRewrite: {
            "^/search": "/",
        },
    })
);

app.use(
    "/api",
    createProxyMiddleware({
        target: "https://api.street-drop.com",
        changeOrigin: true,
        pathRewrite: {
            "^/api": "/",
        },
    })
);

app.get('*', function (req, res) {
    res.sendFile(path.join(__dirname, './build/index.html'));
});

app.listen(3030, function () {
        console.log('Example app listening on port 3030!');
    }
)


module.exports = app;
