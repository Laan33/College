using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Spaceship : MonoBehaviour
{
    public GameObject spaceship;
    public GameObject bullet;
    public static int bulletCount = 0;

    // Start is called before the first frame update
    void Start()
    {


        //Wrap spaceship to other side of screen, check every 0.2 seconds. 5 times a second
        InvokeRepeating("CheckIfOffScreen", 0.2f, 0.2f);

        InvokeRepeating("ResetBulletCount", 1f, 1f);
        
    }

    // Update is called once per frame
    void Update()
    {
        /*apply a physics force to accelerate the spaceship forward if the Up arrow is held, or 
        rotate it left/right if the Left/Right arrows are held.*/

        //Checking if the Up arrow is held, if so check if within velocity limit, if so add force
        if (Input.GetKey(KeyCode.UpArrow) && GetComponent<Rigidbody>().velocity.magnitude < 14)
        {
            GetComponent<Rigidbody>().AddForce(transform.up * 7);            
        }
        if (Input.GetKey(KeyCode.LeftArrow))
        {
            GetComponent<Rigidbody>().AddTorque(transform.forward * -4);
        }
        if (Input.GetKey(KeyCode.RightArrow))
        {
            GetComponent<Rigidbody>().AddTorque(transform.forward * 4);
        }
        
        //Fire bullet if spacebar is pressed - spawn at front of spaceship
        //Position should be positioned and rotated appropriately, with rigidbody given an appropriate velocity
        //Limit of 4 bullets fired per second spaceship.
        if (Input.GetKeyDown(KeyCode.Space) && bulletCount < 4)
        {
            GameObject bullet = Instantiate(Resources.Load("Bullet", typeof(GameObject))) as GameObject;
            bullet.transform.position = spaceship.transform.position + spaceship.transform.up * 1.5f;
            bullet.transform.rotation = spaceship.transform.rotation;
            bullet.GetComponent<Rigidbody>().velocity = spaceship.transform.up * 20;   
            bulletCount++;  
        }
        
        
    }



    void ResetBulletCount()
    {
        bulletCount = 0;
    } 


    // Having the player spaceship respond to moving off-screen, in the same way that asteroids already do
    void CheckIfOffScreen()
    {
        Vector3 currentWorldPos = spaceship.transform.position;
        Vector3 viewPosition = Camera.main.WorldToViewportPoint(currentWorldPos);
        if (viewPosition.x > 1f)
        {
            spaceship.transform.position = new Vector3(-currentWorldPos.x + 1, 0, currentWorldPos.z);
        }
        
        if (viewPosition.y < 0f)
        {
            spaceship.transform.position = new Vector3(currentWorldPos.x, 0, -currentWorldPos.z - 1);
        }

        if (viewPosition.x < 0f)
        {
            spaceship.transform.position = new Vector3(-currentWorldPos.x - 1, 0, currentWorldPos.z);
        }

        if (viewPosition.y > 1f)
        {
            spaceship.transform.position = new Vector3(currentWorldPos.x, 0, -currentWorldPos.z + 1);
        }

        
    }


}
