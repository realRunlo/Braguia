import React, { useContext } from 'react';
import { View, Text, StyleSheet, Image } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { useSelector } from 'react-redux';

import themeContext from '../theme/themeContext';

const History = () => {
  const tripsMetaData = useSelector((state) => state.trips.tripsVal);
  const trailsMetaData = useSelector((state) => state.appData.trails);

  const theme = useContext(themeContext);

  const getTrailById = (trailId) => {
    return trailsMetaData.find((trail) => trail.id === trailId);
  };

  const getPin_name_byId = (pinId, pins) => {
    const pin = pins.find((pin) => pin.id === pinId);
    return pin ? pin.pin_name : null;
  };

  return (
    <View style={[styles.container, { backgroundColor: theme.backgroundColor }]}>
      <View style={styles.content}>
        {tripsMetaData.map((trip, index) => {
          const trail = getTrailById(trip.trailId);
          const pins = trail.edges.map((edge) => edge.edge_start).concat(trail.edges[trail.edges.length - 1].edge_end);
          return (
            <View key={index} style={styles.coolSquare}>
              <View style={styles.item}>
                {trail && <Image source={{ uri: trail.trail_img }} style={styles.image} />}
                <View style={styles.itemContent}>
                  {trail && (
                    <View style={styles.trailInfo}>
                      <Ionicons name="trail-sign-outline" size={20} color="black" style={[styles.icon, { color: theme.color }]} />
                      <Text style={[styles.label, { color: theme.color }]}>{trail.trail_name}</Text>
                    </View>
                  )}
                  {trail && (
                    <View style={styles.trailInfo}>
                      <Ionicons name="alert-circle-outline" size={20} color="black" style={[styles.icon, { color: theme.color }]} />
                      <Text style={[styles.label, { color: theme.color }]}>{trail.trail_difficulty}</Text>
                    </View>
                  )}
                  {trail && (
                    <View style={styles.trailInfo}>
                      <Ionicons name="time-outline" size={20} color="black" style={[styles.icon, { color: theme.color }]} />
                      <Text style={[styles.label, { color: theme.color }]}>{trail.trail_duration} minutes</Text>
                    </View>
                  )}
                  {/* Add more trail data as needed */}
                  <View style={styles.trailInfo}>
                    <Ionicons name="checkmark-done-outline" size={20} color="black" style={[styles.icon, { color: theme.color }]} />
                    <Text style={[styles.label, { color: theme.color }]}>{trip.completePercentage} %</Text>
                  </View>

                  <View style={styles.trailInfo}>
                    <Ionicons name="time-outline" size={20} color="black" style={[styles.icon, { color: theme.color }]} />
                    <Text style={[styles.label, { color: theme.color }]}>Time spent: {trip.timeTaken} miliseconds</Text>
                  </View>

                  <View style={styles.trailInfo}>
                    <Ionicons name="map-outline" size={20} color="black" style={[styles.icon, { color: theme.color }]} />
                    <Text style={[styles.label, { color: theme.color }]}>
                      Visited Places: {trip.visitedPlaces.length > 0 ? trip.visitedPlaces.map((pinId) => getPin_name_byId(pinId, pins)).join(', ') : 'None'}
                    </Text>
                  </View>
                </View>
              </View>
            </View>
          );
        })}
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  content: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  item: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 10,
  },
  image: {
    width: 100,
    height: 150,
    marginRight: 10,
  },
  itemContent: {
    flex: 1,
  },
  trailInfo: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 5,
  },
  icon: {
    marginRight: 5,
  },
  label: {
    fontSize: 14,
    maxWidth: 200,
  },
  text: {
    fontSize: 20,
    fontWeight: 'bold',
  },
  coolSquare: {
    borderWidth: 2,
    width: 350,
    borderColor: 'gray',
    padding: 10,
    borderRadius: 10,
    marginBottom: 10,
  },
});

export default History;
