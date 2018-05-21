import React from "react";

export class DisplayList extends React.Component{

    constructor(){
        super();
        const loadingList = [{"id":"loading", "content":"loading", "source":"loading", "labels":"loading", "originLang":"loading", "actualTime":"loading", "postTime":"loading"}];

        let today = new Date();
        let todayStr = today.getFullYear()+"-"+today.getMonth()+"-"+today.getDate();

        this.state = {
            newsList:loadingList,
            dateStr: todayStr
        };

        this.handlePreviousDate = this.handlePreviousDate.bind(this);
        this.handleNextDate = this.handleNextDate.bind(this);

    }

    componentDidMount(){
        this.fetchData();
    }

    fetchData(){
        const date = getStartEndTime(this.state.dateStr);
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

    handlePreviousDate(){
        let listOfDate = this.state.dateStr.split("-");
        let current = new Date();

        current.setFullYear(parseInt(listOfDate[0]));
        current.setMonth(parseInt(listOfDate[1]));
        current.setDate(parseInt(listOfDate[2]));
        current.setDate(current.getDate()-1);
        let newDateStr = current.getFullYear()+"-"+current.getMonth()+"-"+current.getDate();
        this.setState({dateStr:newDateStr}, function () {
            this.fetchData();
        });
    }

    handleNextDate(){
        let listOfDate = this.state.dateStr.split("-");
        let current = new Date();

        current.setFullYear(parseInt(listOfDate[0]));
        current.setMonth(parseInt(listOfDate[1]));
        current.setDate(parseInt(listOfDate[2]));
        current.setDate(current.getDate()+1);
        let newDateStr = current.getFullYear()+"-"+current.getMonth()+"-"+current.getDate();
        this.setState({dateStr:newDateStr}, function () {
            this.fetchData();
        });
    }

    render(){
        const listItem = this.state.newsList.map((eachNews) =>
            <div key={eachNews.id}>
                <div>{eachNews.content}</div>
                <div>{convertToLocal(eachNews.actualTime)}</div>
            </div>
        );
        if(listItem.length > 0){
            return(
                <div>
                    <button onClick={this.handlePreviousDate}>前一天</button>
                    <span>{changeToDisplay(this.state.dateStr)}</span>
                    <button onClick={this.handleNextDate}>后一天</button>
                    <div>{listItem}</div>
                </div>

            );
        } else {
            return(
                <div>
                    <button onClick={this.handlePreviousDate}>前一天</button>
                    <span>{changeToDisplay(this.state.dateStr)}</span>
                    <button onClick={this.handleNextDate}>后一天</button>
                    <div>No Records for this date</div>
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

function getStartEndTime(dateStr){

    let listOfDate = dateStr.split("-");

    var now = new Date();

    now.setFullYear(parseInt(listOfDate[0]));
    now.setMonth(parseInt(listOfDate[1]));
    now.setDate(parseInt(listOfDate[2]));
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

function changeToDisplay(dateStr) {
    let listStr = dateStr.split("-");
    return listStr[0]+"-"+(parseInt(listStr[1])+1)+"-"+listStr[2];
}

function addZero (number) {
    if(number < 10){
        return "0"+number;
    }
    return number.toString();
}