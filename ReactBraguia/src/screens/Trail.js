import React from 'react';
import { Text, View, StyleSheet, Image } from 'react-native';
import ToggleButton from '../components/ToggleButton';
import PinsSlide from '../components/PinsSlide';
import MapView, { Marker, Polyline } from 'react-native-maps';

const Trail = ({ route }) => {
  const { trail } = route.params;
  const initialRegion = {
    latitude: trail.edges[0].edge_start.pin_lat,
    longitude: trail.edges[0].edge_start.pin_lng,
    latitudeDelta: 0.0922,
    longitudeDelta: 0.0421,
  };
  const coordinates = trail.edges.map((edge) => ({
    latitude: edge.edge_start.pin_lat,
    longitude: edge.edge_start.pin_lng,
  }));

  return (
    <View style={styles.container}>
      <Text style={styles.title}>{trail.trail_name}</Text>
      <Image source={{ uri: trail.trail_img }} style={{ width: 150, height: 150 }} />
      <ToggleButton />
      <MapView style={styles.map} initialRegion={initialRegion}>
        <Polyline coordinates={coordinates} strokeWidth={2} strokeColor="red" />
        { trail.edges.map((edge) => (
          <Marker key={edge.edge_start.id} 
                  coordinate={{
                    latitude: edge.edge_start.pin_lat,
                    longitude: edge.edge_start.pin_lng,
                    }}
                  title={edge.edge_start.title} />
        ))}
      </MapView>
      <PinsSlide pins={trail.edges}/>
      
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    backgroundColor: '#F5F5F5',
    height: 60,
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
    paddingTop: 50,
    paddingBottom: 50,
  },
  map: {
    width: '70%',
    height: '30%',
    marginTop: 20,
  },
});

export default Trail;

