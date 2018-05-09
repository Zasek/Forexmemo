import React from 'react';
import ReactDOM from 'react-dom';

class DisplayList extends React.Component{

    constructor(){
        super();
        const loadingList = [{"id":"loading", "content":"loading", "source":"loading", "labels":"loading", "originLang":"loading", "actualTime":"loading", "postTime":"loading"}];
        this.state = {
            newsList:loadingList
        };
    }

    componentDidMount(){
        fetch('http://localhost:8080/findByDate/2018-05-07', {method: 'GET'})
            .then((response) => {
                return response.json();
            })
            .then((responseJson) => {
                this.setState({newsList:responseJson});
            })
            .catch((error) => {
                console.error(error);
            });
    }

    render(){
        const listItem = this.state.newsList.map(
            (eachNews) => <div key={eachNews.id}><div>{eachNews.content}</div><div>{eachNews.actualTime}</div></div>
        );
        return(
            <div>{listItem}</div>
        );
    };
}

ReactDOM.render(
    <DisplayList />
    ,document.getElementById('root')
);