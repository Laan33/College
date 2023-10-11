using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Spaceship : MonoBehaviour
{
    public GameObject spaceship;

    // Start is called before the first frame update
    void Start()
    {
        //Wrap spaceship to other side of screen, check every 0.2 seconds. 5 times a second
        InvokeRepeating("CheckIfOffScreen", 0.2f, 0.2f);
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
