using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameManagerScript : MonoBehaviour
{
    // Inspector settings (populated by dragging from the hierarchy)
    public GameObject camera, mars;
    public GameObject phobos, deimos;
    public GameObject asteroidObject;
    
    // Use this for initialization
    void Start()
    {
        camera.transform.position = new Vector3(0f, 0f, -200f);
        camera.transform.LookAt(mars.transform);
        mars.GetComponent<Rigidbody>().AddTorque(new Vector3(0f, 20f, 0f));
    }
    // Update is called once per frame
    void Update()
    {

        //mars.GetComponent<Rigidbody>().AddTorque(new Vector3(0f, 20f, 0f));
        //make mars move on its y axis
        mars.transform.RotateAround(Vector3.zero, Vector3.up, 10f * Time.deltaTime);
        phobos.transform.RotateAround(Vector3.zero, Vector3.up, 3f * Time.deltaTime);
        deimos.transform.RotateAround(Vector3.zero, Vector3.up, 2f * Time.deltaTime);
        // NB we are using the camera's own coordinate system (rather than the global coordinate system) to specify the axis of rotation
        if (Input.GetKey(KeyCode.LeftArrow))
            camera.transform.RotateAround(Vector3.zero, camera.transform.up, 50f * Time.deltaTime);
        else if (Input.GetKey(KeyCode.RightArrow))
            camera.transform.RotateAround(Vector3.zero, camera.transform.up, -50f * Time.deltaTime);
        if (Input.GetKey(KeyCode.UpArrow))
            camera.transform.RotateAround(Vector3.zero, camera.transform.right, 50f * Time.deltaTime);
        else if (Input.GetKey(KeyCode.DownArrow))
            camera.transform.RotateAround(Vector3.zero, camera.transform.right, -50f * Time.deltaTime);

        if (Random.Range(0, 150) < 1)
        {
            GameObject asteroid = GameObject.Instantiate(asteroidObject);

            //scale the asteroids to 0.1
            asteroid.transform.localScale = new Vector3(0.1f, 0.1f, 0.1f);

            //asteroid.transform.position = new Vector3(Random.Range(-100, 100), Random.Range(-100, 100), Random.Range(-100, 100));
            asteroid.transform.position = new Vector3(Random.Range(-100, 100), Random.Range(-100, 100), Random.Range(-100, 100));

        }



    }
}


