import React, {Component, useState} from 'react';
import { StyleSheet, Text, View, Platform, Button, Alert, TouchableWithoutFeedback } from 'react-native';

//import 'react-native-gesture-handler';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

const instructions = "Tap on the box when it is your turn";

import {GameState, Seed} from './components/Header';
import {styles} from './components/Styles';
import Cell from './components/Cell';
import GameDetails from './components/GameDetails';



function HomeScreen({ navigation }) {
  return (
    <View style={styles.container}>
      <Text style={styles.welcome}>Welcome!</Text>
      <View style={styles.row}>
        <Button
        title="Login"
        style={styles.button}
        onPress={() => navigation.navigate('Login')}
        />
      </View>

      <View style={styles.row}>
        <Button
        title="Signup"
        onPress={() => navigation.navigate('Signup')}
        />
      </View>

      <View style={styles.row}>
        <Button
        title="Start Game?"
        onPress={() => navigation.navigate('Details')}
        />
      </View>




    </View>
  );
}

function DetailsScreen({ navigation }) {

  const [isGameOver, setIsGameOver] = useState(false);

  return (
    // <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
    //   <Text>Details Screen</Text>
    // </View>
    <GameDetails></GameDetails>
  );
}

function SignupScreen({ navigation }) {
  return (
    <View style={styles.container}>
      <Text style={styles.welcome}>Signup Screen</Text>
    </View>
  );
}

function LoginScreen({ navigation }) {
  return (
    <View style={styles.container}>
      <Text style={styles.welcome}>Login Screen</Text>
    </View>
  );
}

const Stack = createStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen name="Home" component={HomeScreen} options={{ title: 'Tic Tac Toe' }}/>
        <Stack.Screen name="Details" component={DetailsScreen} />
        <Stack.Screen name="Signup" component={SignupScreen} />
        <Stack.Screen name="Login" component={LoginScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
