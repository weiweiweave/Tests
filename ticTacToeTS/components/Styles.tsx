import React, {Component} from 'react';
import { StyleSheet, Text, View, Platform, Button, Alert, TouchableWithoutFeedback } from 'react-native';

export const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  row: {
    justifyContent: 'space-around',
    flexDirection: 'row',
    marginTop: 10
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    marginTop: 0,
    marginRight: 5,
    marginLeft: 5,
    marginBottom: 10
  },
  instructions: {
    fontSize: 12,
    textAlign: 'center',
    color: '#333333',
    marginTop: 0,
    marginRight: 5,
    marginLeft: 5,
    marginBottom: 10
  },
  player: {
    fontSize: 15,
    textAlign: 'center',
    color: '#333333',
    marginTop: 15,
    marginRight: 5,
    marginLeft: 5,
    marginBottom: 0
  },
  square: {
    width: 100,
    height: 100,
    // backgroundColor: 'pink',
    marginRight: 5,
    marginLeft: 5,
    borderWidth: 5,
    borderColor: 'black',
    position: 'relative'
  },
  ring: {
    width: 80,
    height: 80,
    borderRadius: 40,
    borderWidth: 7,
    borderColor: 'red',
    left: 5,
    top: 5,
    alignItems: 'center'
  },
  triangle: {
      width: 0,
      height: 0,
      borderLeftWidth: 40,
      borderLeftColor: 'blue',
      borderRightWidth: 40,
      borderRightColor: 'blue',
      borderTopWidth: 40,
      borderTopColor: 'transparent',
      borderBottomWidth: 40,
      borderBottomColor: 'transparent',
      left: 5,
      top: 5
  },
  cross: {
  },
  crossUp: {
    backgroundColor: 'red',
    height: 100,
    width: 10,
    transform: [
      {rotate: '45deg'}
    ]
  },
  crossFlat: {
    backgroundColor: 'red',
    height: 10,
    width: 100,
    position: 'absolute',
    left: -45,
    top: 45,
    transform: [
      {rotate: '45deg'}
    ]
  },
  button: {
    alignItems: "center",
    backgroundColor: "#DDDDDD",
    padding: 10
  },
  countContainer: {
    alignItems: "center",
    padding: 10
  },
  countText: {
    color: '#FF00FF',
  },
});
