using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameManager : MonoBehaviour
{

    public GameObject asteroidPrefab, spaceshipPrefab;

    public static GameManager instance;
    public static Vector3 screenBottomLeft, screenTopRight;
    public static float screenWidth, screenHeight;
    public static int currentGameLevel;


    // Start is called before the first frame update
    void Start()
    {
        instance = this;

        //Set the current game level to 0
        currentGameLevel = 0;

        /*Camera is positioned at 0,30,0 
         * Facing towards 0,0,0 with 0,0,1 as its 'up' axis */
        Camera.main.transform.position = new Vector3(0, 30, 0);
        Camera.main.transform.LookAt(new Vector3(0, 0, 0), new Vector3(0, 0, 1));


        StartNextLevel();
        //Create a new player spaceship
        CreatePlayerSpaceship();
    }

    void StartNextLevel()
    {
        //Increment the current game level
        currentGameLevel++;
        //Number of asteroids depends on game level
        int numberOfAsteroids = currentGameLevel * 5;

        // find (slightly expanded) screen corners and size, in world coordinates
        // for ViewportToWorldPoint, the z value specified is in world units from the camera
        screenBottomLeft = Camera.main.ViewportToWorldPoint(new Vector3(-0.1f, -0.1f, 30f));
        screenTopRight = Camera.main.ViewportToWorldPoint(new Vector3(1.1f, 1.1f, 30f));
        screenWidth = screenTopRight.x - screenBottomLeft.x;
        screenHeight = screenTopRight.z - screenBottomLeft.z;
        Debug.Log("BottomLeft: " + screenBottomLeft);
        Debug.Log("TopRight: " + screenTopRight);
        Debug.Log("Width: " + screenWidth);
        Debug.Log("Height: " + screenHeight);

        //instantiate a set of asteroids towards the edges of the visible screen using a for loop
        for (int i = 0; i < numberOfAsteroids; i++)
        {
            GameObject go = Instantiate(instance.asteroidPrefab) as GameObject;
            //GameObject asteroid = GameObject.Instantiate(asteroidPrefab);

            float x, z;
            if (Random.Range(0f, 1f) < 0.5f)
                x = screenBottomLeft.x + Random.Range(0f, 0.15f) * screenWidth; // near the left edge
            else
                x = screenTopRight.x - Random.Range(0f, 0.15f) * screenWidth; // near the right edge
            if (Random.Range(0f, 1f) < 0.5f)
                z = screenBottomLeft.z + Random.Range(0f, 0.15f) * screenHeight; // near the bottom edge
            else
                z = screenTopRight.z - Random.Range(0f, 0.15f) * screenHeight; // near the top edge

            go.transform.position = new Vector3(x, 0f, z);

            //scale the asteroid to a random size between 0.2 and 0.35
            go.transform.localScale = new Vector3(Random.Range(0.1f, 0.17f), Random.Range(0.1f, 0.17f), Random.Range(0.1f, 0.17f));
        }
    }

    private static void CreatePlayerSpaceship()
    {
        // instantiate the player's spaceship
        GameObject go = Instantiate(instance.spaceshipPrefab);
        go.transform.position = Vector3.zero;
        go.transform.localScale = new Vector3(0.2f, 0.2f, 0.2f);
    }
}
