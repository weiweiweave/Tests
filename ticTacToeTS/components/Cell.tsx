import React, {Component} from 'react';
import { StyleSheet, Text, View, Platform, Button, Alert, TouchableWithoutFeedback } from 'react-native';

import {GameState, Seed} from './Header';
import {styles} from './Styles';

export default class Cell extends Component {
  //state = { isChecked: false };
  //or

  previousPlayer = Seed.EMPTY;

  constructor(props) {
    super(props);

    this.pressed = false;
    this.state = {
      isCross: false,
      isNought: false,
    };
  }

  onPress = () => {
    // alert('You tapped the button!')
    this.previousPlayer = global.currentPlayer;
    console.log("Click happened by - " + this.previousPlayer);

    //global.game.displayFunction();
    if (!this.pressed){
      console.log(this.previousPlayer +" - Pressed once ");
      this.pressed = true;
      //this.setState({price : this.state.price - 2000});
      if (global.currentPlayer === Seed.CROSS) {
        this.setState(state => {
          return {
            isCross: true,
          };
        });
        global.currentPlayer = Seed.NOUGHT;
      } else if (global.currentPlayer === Seed.NOUGHT) {

        this.setState(state => {
          return {
            isNought: true,
          };
        });
        global.currentPlayer = Seed.CROSS;
      }
    }

    //console.log("changing player - isNought " + this.state.isNought);
    //console.log("changing player - isCross " + this.state.isCross);
    console.log("currentPlayer became - " + global.currentPlayer);
    this.props.updateCell(this.props.row, this.props.column, this.previousPlayer, global.currentPlayer);

  };


  render(props) {
    return (
        // <View style={this.state.isCross ? [styles.ring] : {}} />
        <View style={styles.square}>
          <View style={[this.state.isNought===true ? [styles.ring] : this.state.isCross===true ? [styles.triangle] : {}]}>
          <TouchableWithoutFeedback onPress={this.onPress.bind(this)}>
            <Text></Text>
          </TouchableWithoutFeedback>
          </View>
        </View>

    );
  }
}
