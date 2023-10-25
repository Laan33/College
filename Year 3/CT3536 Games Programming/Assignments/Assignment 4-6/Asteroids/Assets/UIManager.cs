    using System.Collections;
    using System.Collections.Generic;
    using UnityEngine;
    using UnityEngine.UI;
    using TMPro;

    public class UIManager : MonoBehaviour
    {
        public static GameObject instance;

        //UI Panel references
        public GameObject menuPanel;
        public GameObject playingPanel;
        //UI Text references
        public TextMeshProUGUI scoreText;
        public TextMeshProUGUI highScoreText;
        public TextMeshProUGUI livesText;
        public Button startButton;



        void Start()
        {
            instance = this.gameObject;
            
            // //Get references to UI panels and text
            // menuPanel = instance.Find("MenuPanel");
            // playingPanel = instance.Find("PlayingPanel");
            // scoreText = instance.Find("ScoreText").GetComponent<TextMeshProUGUI>();
            // highScoreText = instance.Find("HighScoreText").GetComponent<TextMeshProUGUI>();
            // livesText = instance.Find("LivesText").GetComponent<TextMeshProUGUI>();
        }

        void onClick()
        {
            Debug.Log("Start button clicked");
            GameManager.instance.StartNewGame();
        }

        public void ShowMenu()
        {
            //Show menu panel, hide playing panel
            menuPanel.SetActive(true);
            playingPanel.SetActive(false);
        }

        public void ShowPlaying()
        {
            //Show playing panel, hide menu panel
            menuPanel.SetActive(false);
            playingPanel.SetActive(true);
        }

        public void UpdateScoreText()
        {
            //Update score text
            scoreText.text = "Score: " + GameManager.gameScore;
        }

        public void UpdateHighScoreText()
        {
            //Update high score text
            highScoreText.text = "High Score: " + GameManager.highScore;
        }

        public void UpdateLivesText()
        {
            //Update lives text
            livesText.text = "Lives: " + GameManager.lives;
        }

        public void UpdateUI()
        {
            //Update all UI elements
            UpdateScoreText();
            UpdateHighScoreText();
            UpdateLivesText();
        }

        public void Play_Button_Clicked()
        {
            // //Start the game
            GameManager.instance.StartNewGame();
            Debug.Log("Play button clicked");
            
        }

    

        // Update is called once per frame
        void Update()
        {

        }
    }
