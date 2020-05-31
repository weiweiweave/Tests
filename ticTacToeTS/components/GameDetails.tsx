import React, {Component} from 'react';
import { StyleSheet, Text, View, Platform, Button, Alert, TouchableWithoutFeedback } from 'react-native';

import { useNavigation, NavigationContainer } from '@react-navigation/native';

const instructions = "Tap on the box when it is your turn";

import {GameState, Seed} from './Header';
import {styles} from './Styles';
import Cell from './Cell';

function GoToButton({ screenName }) {
  const navigation = useNavigation();

  return (
    <Button
      title={"Game Over"}
      onPress={() => navigation.navigate(screenName)}
    />
  );
}

export default class GameDetails extends Component {

  // The number of rows and columns
  gridNum = 3;

  constructor(props) {
    super(props);

    global.currentPlayer = Seed.CROSS;

    global.gameBoard = this.setupBoard()

    global.currentState = GameState.PLAYING;

    this.state = {
      //seed: Seed.CROSS,
      seed: global.currentPlayer,
      //board: newBoard,
      cellRow: 0,
      cellColumn: 0,
      gameState: global.currentState,
    };
  }

  setSeed=(childRow, childColumn, dataFromChildPreviousPlayer, dataFromChildNewPlayer)=> {
    this.setState({ seed: dataFromChildNewPlayer });
    //this.setState({ board[childRow][childColumn]= dataFromChildPreviousPlayer });
    this.setState({ cellRow: childRow });
    this.setState({ cellColumn: childColumn });
    global.gameBoard[childRow][childColumn] = dataFromChildPreviousPlayer;
    console.log("setSeed - childRow " + childRow);
    console.log("setSeed - childColumn " + childColumn);
    console.log("setSeed - gameBoard " + global.gameBoard[childRow][childColumn]);
    //check who has won?
    if (this.hasWon(dataFromChildPreviousPlayer, childRow, childColumn)) {
      if (dataFromChildPreviousPlayer===Seed.CROSS) {
        this.setState({ gameState: GameState.CROSS_WON });
      } else if (dataFromChildPreviousPlayer===Seed.NOUGHT) {
        this.setState({ gameState: GameState.NOUGHT_WON });
      }
    }
    //if not, is it draw?
    if (this.isDraw()) {
      this.setState({ gameState: GameState.DRAW });
    }

    //Otherwise, current state == no change
    //still GameState.PLAYING
  }

  setupBoard=() => {
    //create a 2D array
    let board = new Array(this.gridNum)
    for (let i = 0; i < this.gridNum; i++){
       board[i] = new Array(this.gridNum)
    }
    for(let x=0; x< this.gridNum; x++){
      for(let y=0; y < this.gridNum; y++){
        board[x][y] = Seed.EMPTY
      }
    }
    return board
  }

  emptyBoard=() => {
    for (let row = 0; row < this.gridNum; ++row) {
        for (let col = 0; col < this.gridNum; ++col) {
            global.gameBoard[row][col] = Seed.EMPTY
        }
    }
  }

  hasWon=(player, row, column)=> {
    //has Won?
    //3-in-the-row
    if (global.gameBoard[row][0] == player
        && global.gameBoard[row][1] == player
        && global.gameBoard[row][2] == player) {
            return true
    }

    //3-in-the-column
    if (global.gameBoard[0][column] == player
        && global.gameBoard[1][column] == player
        && global.gameBoard[2][column] == player) {
          return true
    }

    //3-in-the-diagonal
    if (global.gameBoard[0][0] == player
        && global.gameBoard[1][1] == player
        && global.gameBoard[2][2] == player) {
          return true
    }

    //3-in-the-opposite-diagonal
    if (global.gameBoard[0][2] == player
        && global.gameBoard[1][1] == player
        && global.gameBoard[2][0] == player) {
          return true
    }
  }

  isDraw=()=> {
    for (let row = 0; row < this.gridNum; ++row) {
        for (let col = 0; col < this.gridNum; ++col) {
            if (global.gameBoard[row][col] == Seed.EMPTY) {
               return false; // an empty cell found, not draw, exit
            }
        }
    }
    return true;  // no more empty cell, it's a draw
  }

  render() {
    return (
      <View style={styles.container}>

        <Text style={styles.instructions}>{instructions}</Text>

        <View style={styles.row}>
          <Cell row="0" column="0" updateCell={this.setSeed.bind(this)}/>
          <Cell row="0" column="1" updateCell={this.setSeed.bind(this)}/>
          <Cell row="0" column="2" updateCell={this.setSeed.bind(this)}/>
        </View>

        <View style={styles.row}>
          <Cell row="1" column="0" updateCell={this.setSeed.bind(this)}/>
          <Cell row="1" column="1" updateCell={this.setSeed.bind(this)}/>
          <Cell row="1" column="2" updateCell={this.setSeed.bind(this)}/>
        </View>

        <View style={styles.row}>
          <Cell row="2" column="0" updateCell={this.setSeed.bind(this)}/>
          <Cell row="2" column="1" updateCell={this.setSeed.bind(this)}/>
          <Cell row="2" column="2" updateCell={this.setSeed.bind(this)}/>
        </View>

        {
          this.state.gameState==GameState.PLAYING ?
            <Text style={styles.player}>Current Player: {this.state.seed}</Text>
            : null
        }

        <Text style={styles.player}>Game State: {this.state.gameState}</Text>

        <View style={styles.row}>
          {
            this.state.gameState!=GameState.PLAYING ?
              <GoToButton screenName="Home" />
              : null
          }
        </View>

      </View>
    );
  }
}
