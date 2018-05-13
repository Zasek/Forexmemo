import React from 'react';
import ReactDOM from 'react-dom';
import {DisplayList} from './components/DisplayList.js'
import {Header} from './components/Header.js'
import {Footer} from './components/Footer.js'

class Page extends React.Component{
    render(){
        return(
            <div>
                <Header />
                <DisplayList />
                <Footer />
            </div>
        );
    };
}

ReactDOM.render(
    <Page />
    ,document.getElementById('root')
);
