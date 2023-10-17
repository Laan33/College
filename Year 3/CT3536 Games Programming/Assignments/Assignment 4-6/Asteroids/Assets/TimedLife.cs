using System.Collections;
using System.Collections.Generic;
using UnityEngine;

/// <summary>
/// Destroys the game object after a random amount of time between minLifetime and maxLifetime.
/// </summary>
public class TimedLife : MonoBehaviour
{
    public float minLifetime, maxLifetime;
    void Start()
    {
        StartCoroutine(HandleLifetime());
    }
    private IEnumerator HandleLifetime()
    {
        yield return new WaitForSeconds(Random.Range(minLifetime, maxLifetime));
        Destroy(gameObject);
    }
}
