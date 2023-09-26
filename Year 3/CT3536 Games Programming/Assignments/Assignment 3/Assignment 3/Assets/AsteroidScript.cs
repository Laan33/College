using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AsteroidScript : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        //initialise the asteroids starting position to somewhere 
        //to the left of mars
        this.transform.position = new Vector3(-100, 0, 0);
        //Set the asteroid moving (using physics) using GetComponent<RigidBody>().AddForce()
        this.GetComponent<Rigidbody>().AddForce(new Vector3(200, 0, 0));
        //Set the asteroid rotating (using physics) 
        this.GetComponent<Rigidbody>().AddTorque(new Vector3(0, 100, 0));
        
    }

    // Update is called once per frame
    void Update()
    {
        if (this.transform.position.x > 300)
        {
            GameObject.Destroy(this.gameObject);
        }
    }

    void OnCollisionEnter() {
        GameObject.Destroy(this.gameObject);
    }


}
