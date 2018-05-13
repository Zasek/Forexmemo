import React from "react";

export class DisplayList extends React.Component{

    constructor(){
        super();
        const loadingList = [{"id":"loading", "content":"loading", "source":"loading", "labels":"loading", "originLang":"loading", "actualTime":"loading", "postTime":"loading"}];
        this.state = {
            newsList:loadingList
        };
    }

    componentDidMount(){
        const date = getStartEndTime();
        fetch('http://localhost:8080/findByDate/'+date, {method: 'GET'})
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
            (eachNews) => <div key={eachNews.id}>
                <div>{eachNews.content}</div>
                <div>{convertToLocal(eachNews.actualTime)}</div>
            </div>
        );
        if(listItem.length > 0){
            return(
                <div>
                    {listItem}
                </div>
            );
        } else {
            return(
                <div>
                    No Records for this date
                </div>
            );
        }
    };
}

function convertToLocal(UTCTime) {
    var time = new Date();
    time.setFullYear(parseInt(UTCTime.substring(0,4),10));
    time.setMonth(parseInt(UTCTime.substring(5,7),10));
    time.setDate(parseInt(UTCTime.substring(8,10),10));
    time.setHours(parseInt(UTCTime.substring(11,13),10));
    time.setMinutes(parseInt(UTCTime.substring(14,16),10));
    time.setSeconds(parseInt(UTCTime.substring(17,19),10));
    var offset = new Date().getTimezoneOffset()* 60 * 1000;
    var localDate = new Date(time.getTime() - offset);
    return addZero(localDate.getHours())+":"+addZero(localDate.getMinutes());
}

function getStartEndTime(){

    var now = new Date();

    now.setHours(0);
    now.setMinutes(0);
    now.setSeconds(0);
    now.setMilliseconds(0);
    var offset = new Date().getTimezoneOffset()* 60 * 1000;
    var startDate = new Date(now.getTime()+offset);
    var startStr = startDate.getUTCFullYear()+"-";
    var actualMonth = startDate.getUTCMonth()+1;
    startStr += addZero(actualMonth)+"-";
    startStr += addZero(startDate.getUTCDate())+" ";
    startStr += addZero(startDate.getUTCHours())+":00:00.000E";

    now.setHours(23);
    now.setMinutes(59);
    now.setSeconds(59);
    now.setMilliseconds(999);
    var endDate = new Date(now.getTime() + offset);
    var endStr = endDate.getUTCFullYear()+"-";
    actualMonth = endDate.getUTCMonth()+1;
    endStr += addZero(actualMonth)+"-";
    endStr += addZero(endDate.getUTCDate())+" ";
    endStr += addZero(endDate.getUTCHours())+":59:59.999";

    return startStr+endStr;
}

function addZero (number) {
    if(number < 10){
        return "0"+number;
    }
    return number.toString();
}