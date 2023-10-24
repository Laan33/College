using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameManager : MonoBehaviour
{

    public GameObject asteroidPrefab, spaceshipPrefab;

    public GameObject uiManager;
    public static GameManager instance;
    public static Vector3 screenBottomLeft, screenTopRight;
    public static float screenWidth, screenHeight;
    public static int currentGameLevel, gameScore, lives, highScore;

    public enum GameState { Menu, Playing }
    GameState currentState = GameState.Menu;

    public UIManager uiManagerScript;

    // Start is called before the first frame update
    void Start()
    {
        instance = this;


        uiManagerScript = uiManager.GetComponent<UIManager>();

        UpdateUI();


    }

    public void UpdateUI()
    {
        switch (currentState)
        {
            case GameState.Menu:
                uiManagerScript.ShowMenu();
                break;
            case GameState.Playing:
                uiManagerScript.ShowPlaying();
                break;
            default:
                break;
        }
    }


    public void StartNewGame()
    {
        currentState = GameState.Playing;
        currentGameLevel = 0;
        gameScore = 0;
        lives = 3;
        UpdateUI();


        Camera.main.transform.position = new Vector3(0, 30, 0);
        Camera.main.transform.LookAt(new Vector3(0, 0, 0), new Vector3(0, 0, 1));

        CreatePlayerSpaceship();
        StartNextLevel();
    }

    void StartNextLevel()
    {
        currentGameLevel++;
        int numberOfAsteroids = currentGameLevel * 5;

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

    public void AddScore(int score)
    {
        gameScore += score;
        if (gameScore > highScore)
            highScore = gameScore;
        uiManagerScript.UpdateHighScoreText();
        uiManagerScript.UpdateScoreText();
    }

    public void SubtractLife()
    {
        lives--;
        uiManagerScript.UpdateLivesText();
        if (lives <= 0)
        {
            GameObject.FindGameObjectsWithTag("Asteroid").ToList().ForEach(asteroid => Destroy(asteroid));
            currentState = GameState.Menu;
            UpdateUI();
        }
    }

    public void AsteroidCheck()
    {
        if (GameObject.FindGameObjectsWithTag("Asteroid").Length == 0)
        {
            StartNextLevel();
        }
    }

}
