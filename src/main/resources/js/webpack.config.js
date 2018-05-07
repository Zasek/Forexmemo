const webpack = require('webpack');

module.exports = {
    mode: 'development',
    entry:[
        './app.js'
    ],
    module:{
        rules:[
            {
                test: /\.(js|jsx)$/,
                exclude: /(node_modules)/,
                loader: "babel-loader",
                options: {
                    presets: ["es2015", "react"]
                }
            }
        ]
    },
    resolve: {
        extensions: ['.js', '.jsx', '.json']
    },
    output: {
        path: __dirname,
        filename: '../static/bundle.js'
    },
};