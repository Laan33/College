using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameManager : MonoBehaviour
{
    //Integer member variable called "currentGameLevel"
    public int currentGameLevel;
    public GameObject asteroidPrefab;
    public GameObject spacefighter;

    // Start is called before the first frame update
    void Start()
    {
        //Create a new player spaceship
        CreatePlayerSpaceship();

        //Set the current game level to 0
        currentGameLevel = 0;

        /*Camera is positioned at 0,30,0 
         * Facing towards 0,0,0 with 0,0,1 as its 'up' axis */
        Camera.main.transform.position = new Vector3(0, 30, 0);
        Camera.main.transform.LookAt(new Vector3(0, 0, 0), new Vector3(0, 0, 1));


        StartNextLevel();
    }

    // Update is called once per frame
    void Update()
    {

    }

    void StartNextLevel()
    {
        //Increment the current game level
        currentGameLevel++;
        //Number of asteroids depends on game level
        int numberOfAsteroids = currentGameLevel * 5;

        //instantiate a set of asteroids towards the edges of the visible screen using a for loop
        for (int i = 0; i < numberOfAsteroids; i++)
        {
            GameObject asteroid = GameObject.Instantiate(asteroidPrefab);
            //scale the asteroid to a random size between 0.2 and 0.35
            asteroid.transform.localScale = new Vector3(Random.Range(0.1f, 0.17f), Random.Range(0.1f, 0.17f), Random.Range(0.1f, 0.17f));
        }
    }

    /* Method which instantiates the player ship in the middle of the screen*/
    void CreatePlayerSpaceship()
    {
        //Create a new player spaceship
        spacefighter = GameObject.Instantiate(spacefighter);
        //Set the player spaceship's position to the center of the screen
        spacefighter.transform.position = new Vector3(0, 0, 0);
        //Scale the player spaceship to a size of 0.2
        spacefighter.transform.localScale = new Vector3(0.2f, 0.2f, 0.2f);

    }


}
